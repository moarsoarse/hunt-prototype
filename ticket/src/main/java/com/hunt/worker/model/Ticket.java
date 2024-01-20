package com.hunt.worker.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hunt.worker.model.AbstractEntity;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@SuperBuilder
@TableName(value = "TICKET")
public class Ticket extends AbstractEntity implements Serializable {
	@TableField(value = "ISSUEDATE")
	private LocalDateTime issuedate;
	
	@TableField(value = "ISSUER")
	@Size(max = 255, message = "max length should less than 255")
	private String issuer;
	
	@TableField(value = "HUNTERID")
	private UUID hunterid;
	
	@TableField(value = "CREATEDDATE")
	private LocalDateTime createddate;
	
	@TableField(value = "UPDATEDDATE")
	private LocalDateTime updateddate;
}