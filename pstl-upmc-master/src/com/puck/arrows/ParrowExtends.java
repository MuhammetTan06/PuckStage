package com.puck.arrows;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.awt.geom.Point2D;

import org.piccolo2d.PNode;
import org.piccolo2d.nodes.PPath;
import org.piccolo2d.nodes.PText;

import com.puck.nodes.piccolo2d.Edge;
import com.puck.nodes.piccolo2d.PiccoloCustomNode;

public class ParrowExtends extends Parrow {
    final static float dash1[] = {10.0f};
    private PPath line;
    private Triangle head = null;
	private static double OFFSET = 12;
	final static BasicStroke dashed =
            new BasicStroke(2.0f,
                            BasicStroke.CAP_BUTT,
                            BasicStroke.JOIN_MITER,
                            10.0f, dash1, 0.0f);
	public ParrowExtends(Point2D from, Point2D to, Point2D virtuaFrom, Point2D virtualTo, boolean edgeViolation, int arrowType) {
		super(from, to, virtuaFrom, virtualTo);
		from = virtuaFrom;
		to = virtualTo;
		this.violation = edgeViolation;
		this.arrowType = arrowType;
		
		line = PPath.createLine(0, 0, 0, 0);
    	head = new Triangle(Color.BLACK, new BasicStroke(1));
		
		Color color = this.violation ? Color.RED : Color.BLACK;
		int thic = this.violation ? 3 : 1;
		draw(from, to, this.arrowType, color, thic);

		
		addChild(line);
		addChild(head);
			
		
		
//		PText text= new PText("arrowExtends");
//		text.setBounds((from.getX()+to.getX())/2, (from.getY()+to.getY())/2, text.getWidth(), text.getHeight());
//		text.rotateInPlace(line.getGlobalRotation());
//		addChild(text);

	}
	
//	public void draw(Point2D f, Point2D t, int arrowType, Paint color, int thickness) {
//		setPosition(f, t);
//		changeStyle(arrowType, color, thickness);
//		
//	}

	public ParrowExtends(PNode from, PNode to, PNode virtualForm, PNode virtualTo, boolean edgeViolation, int arrowType) {
	    this(((PiccoloCustomNode)from).getContent().getText().getGlobalBounds().getCenter2D(),
        		((PiccoloCustomNode)to).getContent().getText().getGlobalBounds().getCenter2D(),
        		((PiccoloCustomNode)virtualForm).getContent().getText().getGlobalBounds().getCenter2D(),
        		((PiccoloCustomNode)virtualTo).getContent().getText().getGlobalBounds().getCenter2D(),
        		edgeViolation,
        		arrowType
        		);
		
		this.from = from;
		this.to = to;
		this.virtualFrom = virtualForm;
		this.virtualto = virtualTo;
	}

	@Override
	public Parrow redraw() {
		removeAllChildren();
		return new ParrowExtends(from, to, from, to, violation, arrowType);
	}

	@Override
	public Parrow redraw(PNode virtuaFrom) {
		removeAllChildren();
		return new ParrowExtends(from, to, virtuaFrom, to, violation, arrowType);
	}

	@Override
	public Parrow redrawTo(PNode virtualTo) {
		removeAllChildren();
		return new ParrowExtends(from, to, virtualFrom, virtualTo, violation, arrowType);
	}


	@Override
	public void draw(Point2D f, Point2D t, int arrowType, Paint color, int thickness) {
		removeChild(line); 
		removeChild(head);
		
		double fromX = f.getX();
		double fromY = f.getY();
		double toX = t.getX();
		double toY = t.getY();
		
		double xDiff = Math.abs(f.getX()-t.getX());
		if(f.distance(t) != 0.0) {
			
			double angle = Math.acos((xDiff / f.distance(t)));
		
			double xOffset = Math.cos(angle) * OFFSET ;
			double yOffset = Math.sin(angle) * OFFSET;
			
			if(fromX-toX<0) {
				fromX += xOffset;
				toX -= xOffset;
			}
			else {
				fromX -= xOffset;
				toX += xOffset;
			}
			if(fromY-toY<0) {
				fromY+=yOffset;
				toY-=yOffset;
			}else {
				fromY-=yOffset;
				toY+=yOffset;
			}
		}
		
		line = PPath.createLine(fromX, fromY, toX, toY);
		
		double theta = Math.atan2(t.getY() - f.getY(), t.getX() - f.getX()) + Math.toRadians(90);
	
		
		if(this.arrowType == VIRTUAL_TYPE) {
			line.setStroke(VIRTUAL_SHAPE);
			head = new Triangle(color, VIRTUAL_SHAPE);
		}
		else {
			line.setStroke(dashed);
			head = new Triangle(color, new BasicStroke(thickness-1));
		}

		line.setStrokePaint(color);
		head.translate(toX, toY);
		head.rotate(theta);

    	addChild(line); 
    	addChild(head);
	}

}
