package emlakcepte.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import emlakcepte.client.Banner;
import emlakcepte.client.BannerServiceClient;
import emlakcepte.configuration.RealtyConverterQueue;
import emlakcepte.controller.UserController;
import emlakcepte.model.Realty;
import emlakcepte.model.User;
import emlakcepte.model.enums.RealtyType;
import emlakcepte.model.enums.UserType;
import emlakcepte.repository.ProductRepository;
import emlakcepte.repository.RealtyRepository;

import emlakcepte.request.RealtyRequest;

@Service
@Component
public class RealtyService {

	private static final int MAX_INDIVICUAL_REALTY_SIZE = 5;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private RealtyConverterQueue realtyConverterQueue;

	@Autowired
	private UserService userService;

	@Autowired
	private RealtyRepository realtyRepository;

	@Autowired
	private BannerServiceClient bannerServiceClient;

	@Autowired
	private ProductRepository productRepository;

	public Realty create(RealtyRequest realtyRequest) {
		Logger logger = Logger.getLogger(UserController.class.getName());

		Optional<User> foundUser = userService.getById(realtyRequest.getUserId());
		if (!foundUser.isPresent()) {
			logger.log(Level.WARNING, "User not found");
			// TODO hata fırlat
			System.out.println("user bulunamadı");

		} else {

			if (UserType.INDIVIDUAL.equals(foundUser.get().getType())) { // en fazla 5 ilan girebilir.

				List<Realty> realtyList = realtyRepository.findAllByUserId(foundUser.get().getId());


				if (productRepository.getProductByUserId(foundUser.get().getId()).getAmount() * 10 <= realtyList
						.size()) {
					// TODO exception fırlatılabilir.
					logger.log(Level.WARNING, "İlan verme sınırına ulaştınız. userID : {0} ", foundUser.get().getId());
				} else {
					Realty realty = convert(realtyRequest);
					realty.setUser(foundUser.get());
					Realty savedRealty = realtyRepository.save(realty);
//					Object result = rabbitTemplate.convertSendAndReceive(realtyConverterQueue.getQueueName(), savedRealty);
//					System.out.println(result);
//					if (result != null) {
//						savedRealty.setStatus(RealtyType.valueOf((String) result));
//					}

					logger.log(Level.INFO, "Realty created. RealtyNo : {0}", realty.getNo());


					Banner bannerRequest = new Banner(String.valueOf(realty.getNo()), 1, "123123", "");
					logger.log(Level.INFO, "Banner created");
					Banner bannerResponse = bannerServiceClient.create(bannerRequest);

					if (bannerResponse.getAdet() > 1) {
						System.out.println("hata verilsin");
					}
					System.out.println("bannerResponse :" + bannerResponse.getAdet());

					return savedRealty;
				}

			}

		}
		return null;
	}

	private Realty convert(RealtyRequest realtyRequest) {
		Realty realty = new Realty();
		realty.setNo(realtyRequest.getNo());
		realty.setCreateDate(LocalDateTime.now());
		realty.setStatus(RealtyType.IN_REVIEW);
		realty.setTitle(realtyRequest.getTitle());
		realty.setProvince(realtyRequest.getProvince());
		return realty;
	}

	public List<Realty> getAll() {
		return realtyRepository.findAll();
	}

	public void getAllByProvince(String province) {

		getAll().stream().filter(realty -> realty.getProvince().equals(province)) //
				// .count();
				.forEach(realty -> System.out.println(realty));

	}
//
//	public List<Realty> getAllByUserName(User user) {
//		return getAll().stream().filter(realty -> realty.getUser().getMail().equals(user.getMail())).toList();
//	}

	public List<Realty> getActiveRealtyByUserName(User user) {
		return getAll().stream().filter(realty -> realty.getUser().getName().equals(user.getName()))
				.filter(realty -> RealtyType.ACTIVE.equals(realty.getStatus())).toList();
	}

	public List<Realty> getAllById(int id) {
		return realtyRepository.findAllByUserId(id);
	}

	public Realty getByNo(int id) {
		return realtyRepository.getByNo(id);
	}

	public List<Realty> getAllActiveRealtyes() {
		return realtyRepository.findAllByStatus(RealtyType.ACTIVE);
	}

	public Realty realtyStatusUpdate(RealtyRequest realtyRequest) {

		// getByNo(realtyRequest.getNo()).setStatus(RealtyType.ACTIVE);
		return getByNo(realtyRequest.getNo());
	}

	public List<Realty> getAllPassiveRealtyes() {
		return realtyRepository.findAllByStatus(RealtyType.PASSIVE);
	}

}
