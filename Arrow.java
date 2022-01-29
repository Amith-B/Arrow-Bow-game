import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
@SuppressWarnings("serial")
public class Arrow extends JPanel
{
	static int x=-200,y=0;
	static JFrame f=new JFrame();
    static JLabel jl=new JLabel();
    static int yboard=126;
    static int yarrow;
    static int arrows=6;
    static int yb;
    Random r=new Random();
    static JButton start = new JButton("START");
    static boolean gameover = false;
    static int ya;
    static int time=5;
    public static int score=0;
    public static int xarrhead,yarrhead,xarrtail,yarrtail,nhead=3,ntails=6,xhead[]=new int[3],yhead[]=new int[3],xtail[]=new int[6],ytail[]=new int[6];;
	Arrow()
	{
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(this);
        f.setTitle("Game");
        
        add(start);
        start.setBounds(100,20,100,25);
        setLayout(null);
        addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent me) // shoot arrow whenever we click on the window
			{
				x=-200;
				new SwingWorker<Void,Void>()
	        	   {
	                   @Override
	                   protected Void doInBackground() throws Exception
	                   {
	                	   yarrow=y+100;
	                	   if(arrows!=0)
	                	   {
	                		   if(yarrow>yboard && yarrow<yboard+140)
	                		   {
	                			   while(x<587)
	                			   {
	                				   x+=4;
	                				   repaint();
	                				   try{Thread.sleep(1);}catch(Exception ex){}
	                			   }
	                		   }
	                		   else
	                		   {
	                			   arrows-=1;
	                			   while(x<840)
	                			   {
	                				   x+=4;
	                				   repaint();
	                				   try{Thread.sleep(1);}catch(Exception ex){}
	                			   }
	                		   }
	                	   }
	                	   else
	                	   {
	                		   score=0;
	                	   }
	                	   try{Thread.sleep(500);}catch(Exception ex){}
	                	   if(x==588 && yarrow>yboard && yarrow<yboard+140)
	                	   {	     
            				   if(yarrow>yboard+50 &&  yarrow<yboard+90)
            				   {   
            					   score=score+6;
            					   arrows+=1;
            				   }
            				   else if(yarrow>yboard+25 && yarrow<yboard+115)
            				   {
            					   score=score+4;
            					   arrows-=1;
            				   }
            				   else
            				   {
            					   score=score+2;
            					   arrows-=1;
            				   }
            				   yarrow=-400;
            				   yb=yboard;
            				   ya=r.nextInt(400);//for allocating random board location
            				   while(ya<60 || ya>250)
	                			   ya=r.nextInt(400);
            				   if(yb<ya)//for moving board slowly
            				   {
            					   while(yb<=ya)
            					   {
            						   yb++;
            						   yboard=yb;
            						   repaint();
            						   try{Thread.sleep(3);}catch(Exception ex){}
            					   }
            				   }
            				   else
            				   {
            					   while(yb>=ya)
            					   {
            						   yb--;
            						   yboard=yb;
            						   repaint();
            						   try{Thread.sleep(3);}catch(Exception ex){}
            					   }
            				   }
            				   if((score>50 && score<57) || (score>150 && score<157) || (score>220 && score<227))
	                			   time--;
	                	   }
	                	   return null;	                	   
	                   }
	        	   }.execute();
			}
		});
        
        setBackground(Color.white);       
        start.addActionListener(new ActionListener()//enable movement of arrow shooter when start is clicked
        		{
        			public void actionPerformed(ActionEvent e)
        			{
        				x=-200;
        				y=0;
        				yboard=126;
        				arrows=6;
        				score=0;
        				gameover = false;
        				start.setVisible(false);
        				new SwingWorker<Void,Void>()
        	        	   {
        	                   @Override
        	                   protected Void doInBackground() throws Exception
        	                   {
        	                	   while(!gameover)
        	                	   {
        	                		   while(y<307 && !gameover)
        	                		   {
        	                			   y++;
        	                			   repaint();
        	                			   try{Thread.sleep(time);}catch(Exception ex){}
        	                		   }
        	                		   while(y!=-50 && !gameover)
        	                		   {
        	                			   y--;
        	                			   repaint();
        	                			   try{Thread.sleep(time);}catch(Exception ex){}
        	                		   }
        	                	   }
        	                	   return null;
        	                   }
        	        	   }.execute();
        			}
        		});
	}
	public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0,y,10,200);//bow
        g.setColor(Color.black);
        g.fillRect(10,y+75,10,50);//bow
        g.setColor(Color.black);
	    g.fillRect(20,y+100,70,5);
	    xarrhead=90;
		yarrhead=y+102;
		xhead[0]=xarrhead;
		xhead[1]=xarrhead+20;
		xhead[2]=xarrhead;
		yhead[0]=yarrhead-7;
		yhead[1]=yarrhead;
		yhead[2]=yarrhead+7;
		g.fillPolygon(xhead,yhead,nhead);//arrowhead
		g.setColor(Color.red);
		g.fillOval(747,yboard+49,50,50);
		Font font=new Font(null,Font.ITALIC,30);
		g.setFont(font);
		if(arrows!=0)
		{
			g.setColor(Color.blue);
			g.drawString("Score="+score+" Arrows="+arrows, 270, 420);
		}
		else
		{
			Font f=new Font(null,Font.BOLD,40);
			g.setFont(f);
			g.setColor(Color.red);
			g.drawString("GAME OVER Score is: "+score, 170, 220);
			gameover = true;
			start.setVisible(true);
			start.setText("Start new game");
			start.setBounds(100,20,150,25);
		}
		g.setColor(Color.black);
	    g.fillRect(x,yarrow,175,5);//arrow
	    g.setColor(Color.black);
	    xarrhead=x+175;
		yarrhead=yarrow+2;
		xhead[0]=xarrhead;
		xhead[1]=xarrhead+20;
		xhead[2]=xarrhead;
		yhead[0]=yarrhead-7;
		yhead[1]=yarrhead;
		yhead[2]=yarrhead+7;
		g.fillPolygon(xhead,yhead,nhead);//arrowhead
		xarrtail=x+5;
		yarrtail=yarrow+2;
		xtail[0]=xarrtail-60;
	    xtail[1]=xarrtail-20;
	    xtail[2]=xarrtail;
	    xtail[3]=xarrtail-20;
	    xtail[4]=xarrtail-60;
	    xtail[5]=xarrtail-40;
	    ytail[0]=yarrtail-7;
	    ytail[1]=yarrtail-7;
	    ytail[2]=yarrtail;
	    ytail[3]=yarrtail+7;
	    ytail[4]=yarrtail+7;
	    ytail[5]=yarrtail;
	    g.fillPolygon(xtail,ytail, ntails);//arrowtail
		g.setColor(Color.black);
		g.fillRect(762,yboard,10,150);//long rect right
		g.setColor(Color.black);
		g.fillRect(772,yboard+50,10,50);//small rect right
		g.setColor(Color.black);
		g.fillRect(782,yboard-100,20,350);
    }
	public static void main(String[] args)
	{
		new Arrow();
        f.setSize(800, 500);
        f.setResizable(false);
        f.setVisible(true);
	}
}
