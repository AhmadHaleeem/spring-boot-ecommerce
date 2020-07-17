package com.haleem.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.haleem.ecommerce.models.Category;
import com.haleem.ecommerce.models.Page;
import com.haleem.ecommerce.repositories.CategoryRepository;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoriesController {

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping
	public String index(Model model) {
		List<Category> categories = categoryRepository.findAllByOrderBySortingAsc();
		model.addAttribute("categories", categories);

		return "admin/categories/index";
	}

	/*
	 * @ModelAttribute("category") public Category getCategory() { return new
	 * Category(); }
	 */
	@GetMapping("/add")
	public String add(Category category) {
		// public String add() {
		return "admin/categories/add";
	}

	@PostMapping("/add")
	public String add(@Valid Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "admin/categories/add";
		}

		redirectAttributes.addFlashAttribute("message", "Category added successfully");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");

		String slug = category.getName().toLowerCase().replace(" ", "-");
		Category categoryExists = categoryRepository.findBySlug(slug);

		if (categoryExists != null) {
			redirectAttributes.addFlashAttribute("message", "Slug exists, please pick up another one");
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			redirectAttributes.addFlashAttribute("category", category);
		} else {
			category.setSlug(slug);
			category.setSorting(100);

			categoryRepository.save(category);
		}

		return "redirect:/admin/categories/add";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		Category category = categoryRepository.getOne(id);
		model.addAttribute("category", category);

		return "/admin/categories/edit";
	}

	@PostMapping("/edit")
	public String edit(@Valid Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model) {
		Category categoryCurrent = categoryRepository.getOne(category.getId());

		if (bindingResult.hasErrors()) {
			model.addAttribute("categoryName", categoryCurrent.getName());
			return "admin/categories/edit";
		}

		redirectAttributes.addFlashAttribute("message", "Category updaed successfully");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");

		String slug = category.getName().toLowerCase().replace(" ", "-");
		Category categoryExists = categoryRepository.findBySlug(slug);

		if (categoryExists != null) {
			redirectAttributes.addFlashAttribute("message", "Slug exists, please pick up another one");
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
		} else {
			category.setSlug(slug);

			categoryRepository.save(category);
		}

		return "redirect:/admin/categories/edit/" + category.getId();
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
		Category category = categoryRepository.getOne(id);
		categoryRepository.deleteById(category.getId());

		redirectAttributes.addFlashAttribute("message", "Category deleted successfully");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/admin/categories/";
	}

	@PostMapping("/reorder")
	public @ResponseBody String reorder(@RequestParam("id[]") int[] id) {
		int count = 1;
		Category category;

		for (int categoryId : id) {
			category = categoryRepository.getOne(categoryId);
			category.setSorting(count);
			categoryRepository.save(category);
			count++;
		}

		return "ok";
	}

}
