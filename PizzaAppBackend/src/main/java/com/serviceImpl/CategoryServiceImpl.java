package com.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advices.CategoryNotFoundException;
import com.dto.CategoryDTO;
import com.entity.Category;
import com.repository.CategoryRepository;
import com.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public CategoryDTO addCategory(CategoryDTO categoryDTO) {
		Category category = new Category();
		category.setCategoryId(categoryDTO.getCategoryId());
		category.setCategoryName(categoryDTO.getCategoryName());
		categoryRepository.save(category);
		return categoryDTO;

	}

	public String updateCategory(int categoryId, CategoryDTO categoryDTO) {
		try {
			Category category = categoryRepository.findById(categoryId)
					.orElseThrow(() -> new CategoryNotFoundException());
			category.setCategoryName(categoryDTO.getCategoryName());

			categoryRepository.save(category);

		} catch (CategoryNotFoundException e) {
			System.out.println(e);
			return "Category not updated";
		}
		return "Updated Successfully";

	}

	public String removeCategory(int categoryId) {

		categoryRepository.deleteById(categoryId);
		return "Deleted Successfully";
	}

	public CategoryDTO seachCategoryByName(String name) {

		Category category = categoryRepository.findByCategoryName(name);

		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryId(category.getCategoryId());
		categoryDTO.setCategoryName(category.getCategoryName());

		return categoryDTO;
	}

	public CategoryDTO searchCategoryById(int id) {
		Category category = categoryRepository.findById(id).get();

		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryId(category.getCategoryId());
		categoryDTO.setCategoryName(category.getCategoryName());

		return categoryDTO;

	}
}
