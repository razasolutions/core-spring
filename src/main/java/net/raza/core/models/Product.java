package net.raza.core.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import org.joda.time.DateTimeZone;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class Product implements IBaseEntity<Long>, IBaseAudited {
	
	// Interface related attributes //
	
    /** The entity id, used to bind database relations */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Version
    private Integer version;
    
    private DateTimeZone createdAt;
    
    @ManyToOne
    private User createdBy;
    
    private DateTimeZone updatedAt;
    
    @ManyToOne
    private User updatedBy;
    
    
	/// Specific attributes //
    
    /** The product id, used to uniquely identify a product in logical context */
    private String productId;
    
    /** The description. */
    private String description;
    
    /** The image url. */
    private String imageUrl;
    
    /** The price. */
    private BigDecimal price;
    
}
