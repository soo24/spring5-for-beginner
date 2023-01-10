package io.namoosori.travelclub.spring.aggregate;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public abstract class Entity {
	// 추상 class => instance object가 만들어질수 X, 상속관계에서 부모클래스의 역할만!, 고유의 id를 갖는 대표성을 가짐

	//
	protected String id;

	protected Entity() {
		//
		this.id = UUID.randomUUID().toString();
	}

	protected Entity(String id) {
		//
		this.id = id;
	}
}
