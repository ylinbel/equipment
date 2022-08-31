package com.mechanicaleng.item;

import com.mechanicaleng.category.CategoryEntity;
import com.mechanicaleng.location.LocationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "item")
public class ItemEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(unique=true)
	private String serial; //serial number - QR code

	@Enumerated(value = EnumType.STRING)
	private StatusEnum statusEnum; //to specify if the item is in available, not available or damaged.

	private String setName; // name of the set

	@ManyToOne(fetch=FetchType.EAGER)
	private LocationEntity location;

	@ManyToOne(fetch=FetchType.EAGER)
	private CategoryEntity category;

	@Enumerated(value = EnumType.STRING)
	private BorrowTermEnum borrowTermEnum;

	private String detailInformation;


	public static ItemEntity fromDto(ItemDto itemDto) {
		return ItemEntity.builder().name(itemDto.getName()).serial(itemDto.getSerial()).borrowTermEnum(itemDto.getBorrowTermEnum()).setName(itemDto.getSetName()).detailInformation(itemDto.getDetailInformation()).build();
	}

	public ItemDto toDto() {
		return ItemDto.builder().id(this.getId()).name(this.getName()).serial(this.getSerial()).borrowTermEnum(this.getBorrowTermEnum()).statusEnum(this.getStatusEnum()).setName(this.getSetName()).location(this.getLocation().toDto()).category(this.getCategory().toDto()).detailInformation(this.getDetailInformation()).build();
	}

	public void updateFromDto(ItemDto itemDto) {
		this.setSetName(itemDto.getSetName());
		this.setName(itemDto.getName());
		this.setSerial(itemDto.getSerial());
		this.setDetailInformation(itemDto.getDetailInformation());
		this.setBorrowTermEnum(itemDto.getBorrowTermEnum());
	}
}
