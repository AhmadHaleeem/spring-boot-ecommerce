package com.haleem.ecommerce.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.haleem.ecommerce.models.Category;
import com.haleem.ecommerce.models.Product;
import com.haleem.ecommerce.repositories.CategoryRepository;
import com.haleem.ecommerce.repositories.ProductRepository;

@Controller
@RequestMapping("/admin/products")
public class AdminProductsController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping
	public String index(Model model, @RequestParam(value = "page", required = false) Integer p) {

		int perPage = 6;
		int page = (p != null) ? p : 0;

		Pageable pageable = PageRequest.of(page, perPage);
		Page<Product> products = productRepository.findAll(pageable);
		model.addAttribute("products", products);

		List<Category> categories = categoryRepository.findAll();
		HashMap<Integer, String> cats = new HashMap<>();

		for (Category category : categories) {
			cats.put(category.getId(), category.getName());
		}
		model.addAttribute("cats", cats);

		Long count = productRepository.count();
		double pageCount = Math.ceil((double) count / (double) perPage);
		model.addAttribute("pageCount", (int) pageCount);
		model.addAttribute("perPage", perPage);
		model.addAttribute("count", count);
		model.addAttribute("page", page);
		
		return "/admin/products/index";
	}

	@GetMapping("/add")
	public String add(Product product, Model model) {

		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("categories", categories);

		return "admin/products/add";
	}

	@PostMapping("/add")
	public String add(@Valid Product product, BindingResult bindingResult, MultipartFile file,
			RedirectAttributes redirectAttributes, Model model) throws IOException {
		List<Category> categories = categoryRepository.findAll();

		if (bindingResult.hasErrors()) {
			model.addAttribute("categories", categories);
			return "admin/products/add";
		}

		boolean fileOk = false;
		byte[] bytes = file.getBytes();
		String fileName = file.getOriginalFilename();
		Path path = Paths.get("src/main/resources/static/media/" + fileName);

		if (fileName.endsWith("jpg") || fileName.endsWith("png")) {
			fileOk = true;
		}

		redirectAttributes.addFlashAttribute("message", "Product added successfully..");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");

		String slug = product.getName().toLowerCase().replace(" ", "-");
		Product productExists = productRepository.findBySlug(slug);

		if (!fileOk) {
			redirectAttributes.addFlashAttribute("message", "Image must be a jpg or png");
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			redirectAttributes.addFlashAttribute("product", product);
		} else if (productExists != null) {
			redirectAttributes.addFlashAttribute("message", "Product already exists, please pick up another one");
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			redirectAttributes.addFlashAttribute("product", product);
		} else {
			product.setSlug(slug);
			product.setImage(fileName);

			productRepository.save(product);
			Files.write(path, bytes);
		}

		return "redirect:/admin/products/add";
	}

	@GetMapping("/edit/{id}")
	public String eidt(@PathVariable int id, Model model) {
		Product product = productRepository.getOne(id);
		model.addAttribute("product", product);

		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("categories", categories);

		return "admin/products/edit";
	}

	@PostMapping("/edit")
	public String edit(@Valid Product product, BindingResult bindingResult, MultipartFile file,
			RedirectAttributes redirectAttributes, Model model) throws IOException {
		Product currentProduct = productRepository.getOne(product.getId());

		List<Category> categories = categoryRepository.findAll();

		if (bindingResult.hasErrors()) {
			model.addAttribute("productName", currentProduct.getName());
			model.addAttribute("categories", categories);
			return "admin/products/add";
		}

		boolean fileOk = false;
		byte[] bytes = file.getBytes();
		String fileName = file.getOriginalFilename();
		Path path = Paths.get("src/main/resources/static/media/" + fileName);

		if (!file.isEmpty()) {
			if (fileName.endsWith("jpg") || fileName.endsWith("png")) {
				fileOk = true;
			}
		} else {
			fileOk = true;
		}

		redirectAttributes.addFlashAttribute("message", "Product updated successfully..");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");

		String slug = product.getName().toLowerCase().replace(" ", "-");
		Product productExists = productRepository.findBySlugAndIdNot(slug, product.getId());

		if (!fileOk) {
			redirectAttributes.addFlashAttribute("message", "Image must be a jpg or png");
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			redirectAttributes.addFlashAttribute("product", product);
		} else if (productExists != null) {
			redirectAttributes.addFlashAttribute("message", "Product already exists, please pick up another one");
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			redirectAttributes.addFlashAttribute("product", product);
		} else {
			product.setSlug(slug);

			if (!file.isEmpty()) {
				Path path2 = Paths.get("src/main/resources/static/media/" + currentProduct.getImage());
				Files.delete(path2);

				product.setImage(fileName);
				Files.write(path, bytes);
			} else {
				product.setImage(currentProduct.getImage());
			}

			productRepository.save(product);
		}

		return "redirect:/admin/products/edit/" + product.getId();
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) throws IOException {
		Product product = productRepository.getOne(id);
		Product currentProduct = productRepository.getOne(product.getId());

		productRepository.deleteById(id);

		Path path2 = Paths.get("src/main/resources/static/media/" + currentProduct.getImage());
		Files.delete(path2);

		redirectAttributes.addFlashAttribute("message", "Product deleted successfully..");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");

		return "redirect:/admin/products";
	}

}
