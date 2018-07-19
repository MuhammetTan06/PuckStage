package com.puck.arrows;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.awt.geom.Point2D;

import org.piccolo2d.PNode;
import org.piccolo2d.nodes.PPath;

public abstract class Parrow extends PNode{
	final static float dash1[] = {2.0f};
	final static BasicStroke VIRTUAL_SHAPE =
            new BasicStroke(2.0f,
                            BasicStroke.CAP_BUTT,
                            BasicStroke.JOIN_MITER,
                            10.0f, dash1, 0.0f);
	public static final int VIRTUAL_TYPE = 0;
	public static final int REAL_TYPE = 1;
	
    public Parrow(Point2D from, Point2D to,Point2D virtuaFrom,Point2D virtualTo){
    	line = PPath.createLine(0.0, 0.0, 0.0, 0.0);
    }
    protected PNode virtualFrom;
    protected PNode virtualto;
    protected PNode from;
    protected PNode to;
    protected boolean isAllowed;
    protected boolean violation;
    protected int arrowType = REAL_TYPE;;
    protected PPath line;;
    
    
  
    public Paint getColor() {
    	return line.getStrokePaint();
    }

    public boolean getViolation() {
		return violation;
	}

	public void setViolation(boolean violation) {
		this.violation = violation;
	}

	public PNode getFrom() {
        return from;
    }

    public PNode getTo() {
        return to;
    }

    public void setFrom(PNode from){
        this.from=from;
    }

    public void setTo(PNode to){
        this.to=to;
    }

    public Parrow(PNode from,PNode to,PNode virtuaFrom,PNode virtualto){
        this.from=from;
        this.to=to;
        this.virtualFrom = virtuaFrom;
        this.virtualto = virtualto;
        this.isAllowed = true;
        this.violation = false;
        
    }
    public abstract void draw(Point2D from, Point2D to, int arrowType, Paint color, int thickness);
    
    
    public abstract Parrow redraw();
    public abstract Parrow redraw(PNode virtuaFrom);
    public abstract Parrow redrawTo(PNode virtuaFrom);

    @Override
    public boolean equals(Object arrow){
        if(!(arrow instanceof Parrow))
            return false;
        Parrow p = (Parrow) arrow;
        return this.from==p.from
                &&this.to==p.to
               ;
    }

	public PNode getVirtualFrom() {
		return virtualFrom;
	}

	public void setVirtualFrom(PNode virtualFrom) {
		this.virtualFrom = virtualFrom;
	}

	public PNode getVirtualto() {
		return virtualto;
	}

	public void setVirtualto(PNode virtualto) {
		this.virtualto = virtualto;
	}

	public boolean isAllowed() {
		return isAllowed;
	}

	public void setAllowed(boolean isAllowed) {
		this.isAllowed = isAllowed;
	}

	public void setLine(PPath line2) {
		this.line = line2;
		
	}

	public void setArrowType(int type) {
		this.arrowType = type;	
	}
	public int getArrowType() {
		return this.arrowType;
	}
	
    
}
