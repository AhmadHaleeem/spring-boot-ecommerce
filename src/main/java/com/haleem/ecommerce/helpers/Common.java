package com.haleem.ecommerce.helpers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.haleem.ecommerce.models.Page;
import com.haleem.ecommerce.repositories.PageRepository;

@ControllerAdvice
public class Common {

	@Autowired
	private PageRepository pageRepository;

	@ModelAttribute
	public void sharedData(Model model) {
		List<Page> pages = pageRepository.findAllByOrderBySortingAsc();

		model.addAttribute("cpages", pages);

	}

}
