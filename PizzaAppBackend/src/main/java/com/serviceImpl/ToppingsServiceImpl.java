package com.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advices.ToppigsNotFoundException;
import com.dto.ToppingsDTO;
import com.entity.Toppings;
import com.repository.ToppingsRepository;
import com.service.ToppingsService;

@Service
public class ToppingsServiceImpl implements ToppingsService {

	@Autowired
	private ToppingsRepository toppingsRepository;

	@Override
	public ToppingsDTO addToppings(ToppingsDTO toppingsDTO) {
		Toppings toppings = new Toppings();
		toppings.setToppingsId(toppings.getToppingsId());
		toppings.setToppingsName(toppingsDTO.getToppingsName());
		toppings.setToppingsPrice(toppingsDTO.getToppingsPrice());
		toppingsRepository.save(toppings);
		return toppingsDTO;
	}

	@Override
	public String updateToppings(int toppingsId, ToppingsDTO toppingsDTO) {
		try {
			Toppings toppings = toppingsRepository.findById(toppingsId)
					.orElseThrow(() -> new ToppigsNotFoundException());
			toppings.setToppingsName(toppingsDTO.getToppingsName());
			toppings.setToppingsPrice(toppingsDTO.getToppingsPrice());
			toppingsRepository.save(toppings);

		} catch (ToppigsNotFoundException e) {
			System.out.println(e);
			return "Toppings not updated";
		}
		return "Updated Successfully";
	}

	@Override
	public String removeToppings(int toppingsId) {

		toppingsRepository.deleteById(toppingsId);
		return "Deleted Successfully";
	}

	@Override
	public ToppingsDTO searchToppingsById(int toppingsId) {
		Toppings toppings = toppingsRepository.findById(toppingsId).get();

		ToppingsDTO toppingsDTO = new ToppingsDTO();
		toppingsDTO.setToppingsId(toppings.getToppingsId());
		toppingsDTO.setToppingsName(toppings.getToppingsName());

		return toppingsDTO;

	}

}
