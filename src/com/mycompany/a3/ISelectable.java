package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public interface ISelectable {
	
	public void setSelected(boolean b);
	public boolean isSelected();
	abstract boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt);
	abstract void draw(Graphics g, Point pCmpRelPrnt);

}
