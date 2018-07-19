package com.puck.arrows;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.geom.Point2D;

import org.piccolo2d.PNode;
import org.piccolo2d.nodes.PPath;
import org.piccolo2d.nodes.PText;

import com.puck.nodes.piccolo2d.Edge;
import com.puck.nodes.piccolo2d.PiccoloCustomNode;

public class ParrowUses extends Parrow{

    private double spacing;
    private TriangleHollow head;
   
  
    private static double OFFSET = 12;
    	
   
    
    public ParrowUses(Point2D from, Point2D to, double spacing,Point2D virtuaFrom,Point2D virtualTo, boolean edgeViolation, int arrowType){
    	super(from, to, virtuaFrom, virtualTo);
		from = virtuaFrom;
		to = virtualTo;
		
    	this.arrowType = arrowType;
    	this.violation = edgeViolation;
    	line = PPath.createLine(0, 0, 0, 0);
    	head = new TriangleHollow(Color.RED, new BasicStroke(1));
		
    	Color color = this.violation ? Color.RED : Color.BLACK;
		int thic = this.violation ? 3 : 1;
		draw(from, to, this.arrowType, color, thic);
			
		
		addChild(line);
		addChild(head);
    }

  
    

    public ParrowUses(PNode from, PNode to, double spacing, PNode virtualForm, PNode virtualTo, boolean edgeViolation, int arrowType){
        this(((PiccoloCustomNode)from).getContent().getText().getGlobalBounds().getCenter2D(),
        		((PiccoloCustomNode)to).getContent().getText().getGlobalBounds().getCenter2D(),
        		spacing,
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
        return new ParrowUses(from,to,spacing,from,to, violation, arrowType);
    }

	@Override
	public Parrow redraw(PNode virtuaFrom) {
		
		removeAllChildren();
        return new ParrowUses(from,to,spacing,virtuaFrom,virtualto, violation, arrowType);
	}
	@Override
	public Parrow redrawTo(PNode virtualTo) {
		removeAllChildren();
        return new ParrowUses(from,to,spacing,virtualFrom,virtualTo, violation, arrowType);
	}
	
	public void addCard(int from, int to) {
		System.out.println("j'ajoute la cardinalité ");
		PText text= new PText("<"+from+","+to+">");
		text.setFont(new Font("Courier New", Font.BOLD, 18));
		text.setBounds(line.getGlobalFullBounds().getCenterX(),line.getGlobalFullBounds().getCenterY() , text.getWidth(), text.getHeight());
		addChild(text);
	}


	@Override
	public void draw(Point2D f, Point2D t, int arrowType, Paint color, int thickness) {
		removeChild(line); 
		removeChild(head);
//		head = new TriangleHollow(Color.BLACK, new BasicStroke(1));
		double fromX = f.getX();
		double fromY = f.getY();
		double toX = t.getX();
		double toY = t.getY();
		
		
		double xDiff = Math.abs(f.getX()-t.getX());
		if(f.distance(t) != 0.0) {
			
			double angle = Math.acos((xDiff / f.distance(t)));
			
			double xOffset = Math.cos(angle) * OFFSET;
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
			head = new TriangleHollow(color, VIRTUAL_SHAPE);
		}
		else {
			line.setStroke(new BasicStroke(thickness));
			head = new TriangleHollow(color, new BasicStroke(thickness));
		}

		line.setStrokePaint(color);
		head.translate(toX, toY);
		head.rotate(theta);
		
		
		
    	addChild(line); 
    	addChild(head);		
	
	
	}
		
	
	
}

