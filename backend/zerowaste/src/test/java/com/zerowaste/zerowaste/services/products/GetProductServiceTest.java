package com.zerowaste.zerowaste.services.products;

import com.zerowaste.dtos.products.CreateProductDTO;
import com.zerowaste.dtos.products.GetProductsRequestQueryDTO;
import com.zerowaste.dtos.products.GetProductsResponseBodyDTO;
import com.zerowaste.models.product.Product;
import com.zerowaste.models.product.ProductCategory;
import com.zerowaste.models.product.ProductStatus;
import com.zerowaste.repositories.ProductsRepository;
import com.zerowaste.services.products.GetProductService;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetProductServiceTest {

    @InjectMocks
    private GetProductService sut;

    @Mock
    private ProductsRepository productsRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("It should be able to get a product by expiration days")
    void itShouldGetProduct() {
        // Arrange
        var dto = new CreateProductDTO(
            "Product Name",
            "Product Description",
            "Product Brand",
            ProductCategory.BAKERY.getCategory(),
            10.0,
            10,
            LocalDate.now().plusDays(1)
        );

        var product = new Product();

        Integer daysToExpire = 2;
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setBrand(dto.brand());
        product.setCategory(ProductCategory.valueOf(dto.category()));
        product.setUnitPrice(dto.unitPrice());
        product.setStock(dto.stock());
        product.setExpiresAt(dto.expiresAt());
        product.setStatus(ProductStatus.AVALIABLE);

        var dtoGet = new GetProductsRequestQueryDTO(daysToExpire);

        when(productsRepository.findAllNotDeleted(daysToExpire)).thenReturn(List.of(product));

        GetProductsResponseBodyDTO response = new GetProductsResponseBodyDTO(
            null, 
            dto.name(), 
            dto.description(), 
            dto.brand(), 
            ProductCategory.valueOf(dto.category()), 
            dto.unitPrice(), 
            null, 
            dto.stock(), 
            dto.expiresAt(), 
            ProductStatus.AVALIABLE);
        
        // Act & Assert
        List<GetProductsResponseBodyDTO> result = assertDoesNotThrow(() -> sut.execute(dtoGet));
        assertEquals(response, result.get(0));
        verify(this.productsRepository, times(1)).findAllNotDeleted(daysToExpire);
    }
}