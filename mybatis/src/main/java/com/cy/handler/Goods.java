package com.cy.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods {
	private Integer id;
	private String name;
	private Status status=Status.UNPUBLISHED;
}
