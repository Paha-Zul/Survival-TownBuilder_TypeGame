package com.mygdx.game.interfaces;

import com.mygdx.game.component.Component;
import com.mygdx.game.entity.Entity;

public interface Condition {
	public <T extends Component> boolean condition(T t, int val);
}
