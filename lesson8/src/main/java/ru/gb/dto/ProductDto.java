package ru.gb.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class ProductDto {

    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    @Min(0)
    @Max(100000)
    private BigDecimal price;

    public ProductDto() {
    }

    public ProductDto(String title, BigDecimal price){
        this.title = title;
        this.price = price;
    }

    public ProductDto(Long id, String title, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
