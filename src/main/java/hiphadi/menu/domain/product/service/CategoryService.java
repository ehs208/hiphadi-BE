package hiphadi.menu.domain.product.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hiphadi.menu.domain.product.domain.Category;
import hiphadi.menu.domain.product.domain.repository.CategoryRepository;
import hiphadi.menu.global.exception.CustomException;
import hiphadi.menu.global.exception.ErrorCode;
import hiphadi.menu.domain.product.dto.CategoryReorderRequest;
import hiphadi.menu.domain.product.dto.CategoryRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public List<Category> getAllCategories() {
		return categoryRepository.findAllByOrderByPriorityAsc();
	}

	@Transactional
	@CacheEvict(value = "MENU", allEntries = true)
	public Category createCategory(CategoryRequest request) {
		long maxPriority = categoryRepository.count();
		Category category = Category.builder()
			.categoryName(request.getCategoryName())
			.categoryEngName(request.getCategoryEngName())
			.priority(maxPriority + 1)
			.build();
		return categoryRepository.save(category);
	}

	@Transactional
	@CacheEvict(value = "MENU", allEntries = true)
	public Category updateCategory(Long id, CategoryRequest request) {
		Category category = categoryRepository.findById(id)
			.orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));
		category.updateInfo(request.getCategoryName(), request.getCategoryEngName());
		return category;
	}

	@Transactional
	@CacheEvict(value = "MENU", allEntries = true)
	public void deleteCategory(Long id) {
		Category category = categoryRepository.findById(id)
			.orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND));
		categoryRepository.delete(category);
	}

	@Transactional
	@CacheEvict(value = "MENU", allEntries = true)
	public void reorderCategories(CategoryReorderRequest request) {
		List<Long> categoryIds = request.getCategoryIds();
		Map<Long, Category> categoryMap = categoryRepository.findAllById(categoryIds)
			.stream()
			.collect(Collectors.toMap(Category::getId, Function.identity()));

		for (int i = 0; i < categoryIds.size(); i++) {
			Category category = categoryMap.get(categoryIds.get(i));
			if (category == null) {
				throw new CustomException(ErrorCode.CATEGORY_NOT_FOUND);
			}
			category.updatePriority((long) (i + 1));
		}
	}
}
