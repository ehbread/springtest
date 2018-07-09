package com.example.test.my.entity;

import java.util.Date;

import javax.persistence.*;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@Table(name = "bookmark")
@EntityListeners(AuditingEntityListener.class)
public class Bookmark {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bookmarkId;

	@Column(nullable = false, length=1000)
	@NotEmpty
	private String title;

	@Column(nullable = false)
	private Long memberId;

	@CreatedDate
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;


	@LastModifiedDate
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date modifiedDate;

	public String getCreatedDateFormat() {
		return DateFormatUtils.format(createdDate, "yyyy-MM-dd(E) HH:mm:ss");
	}

}