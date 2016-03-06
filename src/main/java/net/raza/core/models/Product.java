package net.raza.core.models;

import java.math.BigDecimal;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.raza.core.enums.YesOrNoEnum;

/**
 * 
 * Product entity. Relative to every item that an @Customer can add to his shopping cart.
 * 
 * @author tazabreu
 *
 */

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Product extends AuditedEntity<Long> {
	
    
    /** The product id, used to uniquely identify a product in logical context */
    private String productId;
    
    /** The description. */
    private String description;
    
    /** The image url. */
    private String imageUrl;
    
    /** The price. */
    private BigDecimal price;
    
    /** The active. */
    private YesOrNoEnum active;
    
}
