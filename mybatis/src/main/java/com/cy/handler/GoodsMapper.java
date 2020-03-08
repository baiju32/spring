package com.cy.handler;

import java.util.List;

public interface GoodsMapper {
	List<Goods> findAll();
	int insertGoods(Goods goods);
}
