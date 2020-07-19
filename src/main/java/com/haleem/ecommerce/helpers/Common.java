package com.haleem.ecommerce.helpers;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.haleem.ecommerce.models.Cart;
import com.haleem.ecommerce.models.Category;
import com.haleem.ecommerce.models.Page;
import com.haleem.ecommerce.repositories.CategoryRepository;
import com.haleem.ecommerce.repositories.PageRepository;

@ControllerAdvice
@SuppressWarnings("unchecked")
public class Common {

	@Autowired
	private PageRepository pageRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@ModelAttribute
	public void sharedData(Model model, HttpSession session, Principal principal) {
		
		// it's loggedIn [user || admin]
		if (principal != null) {
			model.addAttribute("loggedInUser", principal.getName());
		}
		
		List<Page> pages = pageRepository.findAllByOrderBySortingAsc();
		List<Category> categories = categoryRepository.findAll();

		boolean cartActive = false;
		if (session.getAttribute("cart") != null) {
			HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
			
			int size = 0;
			double total = 0;
			for (Cart value : cart.values()) {
				size += value.getQuantity();
				total += value.getQuantity() * Double.parseDouble(value.getPrice());
			}
			
			model.addAttribute("csize", size);
			model.addAttribute("ctotal", total);
			
			cartActive = true;
		}

		model.addAttribute("cpages", pages);
		model.addAttribute("ccategories", categories);
		model.addAttribute("cartActive", cartActive);
	}

}
