import java.util.*;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;

import javax.swing.*;
import java.awt.Font;

public class Final_project extends JFrame{
	
	static JLabel label;
	static JLabel info;
	static int flag=0; //양수이면 0
	static int check=0;
	static JLabel blank;
	
	public Final_project() {
		setTitle("계산기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //메인프레임 안전하게 닫기 

		
		Container c = getContentPane();
		c.setLayout(new BorderLayout(5,5));
		c.setBackground(Color.gray);
		//c.setLayout(new GridLayout(2,1));
		//c.setLayout(new GridLayout(5,1,10,10));
		
		NorthPanel NP = new NorthPanel();
		c.add(NP,BorderLayout.NORTH);
		
		CenterPanel CP = new CenterPanel();
		c.add(CP,BorderLayout.CENTER);
		//c.add(CP,GridLayout);
		
		
		ResultPanel RP = new ResultPanel();
		c.add(RP,BorderLayout.SOUTH);
		
		setSize(280,500); //프레임의 크기 
		
		//setLayout(new FlowLayout());
		//JButton button = new JButton("버튼");
		//add(button);
		setVisible(true); //프레임에 화면 나타내기(Jframe의 메서드)
	}
	
	class NorthPanel extends JPanel{ //상단 설명패널 클래스 설정 
		public NorthPanel() {
			setLayout(new GridLayout(4,1));
			setBackground(Color.gray); //상단 배경 
			setForeground(Color.red);//..안먹음 


			info = new JLabel("계산할 수와 연산자를 입력하세요");
			label = new JLabel("");
			blank = new JLabel("_");

			info.setOpaque(true);
			label.setOpaque(true);
			
			info.setFont(new Font("나눔명조",Font.PLAIN,17));
			info.setBackground(Color.gray); //배경색 
			info.setForeground(Color.white); //글자색 
			info.setHorizontalAlignment(SwingConstants.RIGHT);
			
			label.setFont(new Font("나눔명조",0,40));
			label.setBackground(Color.gray);
			label.setForeground(Color.white);
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			
			/*blank.setFont(new Font("나눔명조",Font.PLAIN,20));
			blank.setHorizontalAlignment(SwingConstants.RIGHT);
			*/
			
			add(info);
			add(label);
			//add(blank);
		//	label.addMouseListener(new My());
	}
	}
	
	class ResultPanel extends JPanel{
		public ResultPanel() {
			JButton[] btt = new JButton[2];
			setBackground(Color.black); //하단 배경색 
			//setForeground(Color.white); 
			setLayout(new GridLayout(2,1,0,0));
			
			btt[0]= new JButton("c");
			btt[1]= new JButton("=");
			
			add(btt[0]);
			add(btt[1]);
			
			for(int i=0; i<2; i++)
			{
				if(i==0)
				{
					btt[0].setFont(new Font("Apple SD 산돌고딕 Neo",0,24));//=.c폰트 크기 
					//btt[0].setBackground(Color.gray);
					btt[0].setForeground(new Color(37,36,36));
					add(btt[i]);
					
					btt[0].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int n = label.getText().length()-1;
							
							if(n==0) {
								label.setText("");
								info.setFont(new Font("나눔고딕",0,17));
								info.setForeground(Color.white);
								info.setText("계산할 수와 연산자를 입력하세요");
								
								flag=0;
							}
							else if(0<n && n<=10)
							{
								label.setFont(new Font("Apple SD 산돌고딕 Neo",0,40));
								label.setText(label.getText().substring(0,n));
								info.setForeground(Color.white);  
								info.setFont(new Font("나눔고딕",0,20));
								info.setText("수식을 지우는 중입니다.");
							}
							else {
								label.setFont(new Font("Apple SD 산돌고딕 Neo",0,40));
								label.setText(label.getText().substring(0,n));
								info.setForeground(Color.white);
								info.setFont(new Font("나눔고딕",0,20));
								info.setText("수식을 지우는 중입니다.");
							}
						}
					});
				
				}
				else 
					btt[1].setForeground(new Color(37,36,36));
					btt[1].addActionListener(new CalcListener());
					
			}
		}
	}
	class CenterPanel extends JPanel{ //센터패널(버튼출력부분) 
		public CenterPanel() {
			JButton[] bt = new JButton[16];
			setBackground(Color.black); //중앙부 뒷배경 
			setLayout(new GridLayout(4,4,0,0));
			
			
			bt[0] = new JButton("7");
			bt[1] = new JButton("8");
			bt[2] = new JButton("9");
			bt[3] = new JButton("/");
			
			bt[4] = new JButton("4");
			bt[5] = new JButton("5");
			bt[6] = new JButton("6");
			bt[7] = new JButton("x");
			
			bt[8] = new JButton("1");
			bt[9] = new JButton("2");
			bt[10] = new JButton("3");
			bt[11] = new JButton("-");
			
			bt[12] = new JButton("^");
			bt[13] = new JButton("0");
			bt[14] = new JButton(".");
			bt[15] = new JButton("+");
			
			
			
			for(int i=0; i<16; i++) 
			{
				if(i%4!=3 && i<11) //숫자 
				{
					bt[i].setFont(new Font("나눔고딕",0,25));
					//bt[i].setBackground(Color.orange);
					bt[i].setForeground(new Color(85,14,14));//글씨가 뭍
					add(bt[i]);
					
					bt[i].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(flag==0) {
								JButton b = (JButton)e.getSource();
								String oldtext = label.getText();
								String text = b.getText();
								String newtext = oldtext + text; //입력받으면 스트링에 누적해서 합치기 
							
								int n = newtext.length(); //입력되고 있는 문자의 길이 
								if(n<=10)label.setFont(new Font("나눔고딕",0,40));
								else if(n>10)
									label.setFont(new Font("나눔고딕",0,30));
								
								if(n<=25)
								{
									label.setText(newtext);
									info.setForeground(Color.white);
									info.setFont(new Font("나눔고딕",0,20));
									info.setText("계산중입니다.");
								}
								else if(n>25) {
									info.setFont(new Font("나눔고딕",0,17));
									//info.setForeground(Color.black);
									info.setForeground(new Color(85,14,14));
									info.setText("입력 가능한 범위를 초과하였습니다.");
								}
							}
						}
					});		
				}
				
				else if(i==14) // .일 때 
				{
					bt[i].setFont(new Font("Apple SD 산돌고딕 Neo",0,20));
					//bt[i].setBackground(Color.gray);
					add(bt[i]);
					
					//bt[14].addActionListener(new MyListener());
					bt[14].addActionListener(new ActionListener()  //눌리면 
					{
						public void actionPerformed(ActionEvent e) {
							if(flag==0) //양수라면 
							{
								JButton b = (JButton)e.getSource();
								int n = label.getText().length();
								Character c = label.getText().charAt(n-1); //첫글자 c에 저장 
								if(c!='.') {  //첫글자가 .이 아니라면 스트링에 저장 
									String oldtext = label.getText();
									String text = b.getText();
									String newtext=oldtext+text;
									label.setText(newtext);
									info.setForeground(Color.white);
									info.setFont(new Font("나눔고딕",0,20));
									info.setText("계산중입니다.");
								
								//JButton b = (JButton)e.getSource();
							
								int len = newtext.length(); //입력되고 있는 문자의 길
								if(len<10)label.setFont(new Font("Apple SD 산돌고딕 Neo",0,40));
								else if(n>10)
									label.setFont(new Font("Apple SD 산돌고딕 Neo",0,30));
								
								if(len<=25)
								{
									label.setText(newtext);
									info.setForeground(Color.white);
									info.setFont(new Font("나눔고딕",0,20));
									info.setText("계산중입니다.");
								
								}
								else if(len>25) {
									info.setFont(new Font("나눔고딕",0,17));
									//info.setForeground(Color.white);
									info.setForeground(new Color(85,14,14));
									info.setText("입력 가능한 범위를 초과하였습니다.");
									
								}
								}
							}
						}
					});
				}
				else if(i%4==3||i==12) { //연산자일 때  
					bt[i].setFont(new Font("Apple SD 산돌고딕 Neo",0,25));
					bt[i].setForeground(Color.black);
					add(bt[i]);
					bt[i].addActionListener(new MyListener());
				}
				else if(i==13) { //0일 때 
					bt[i].setFont(new Font("Apple SD 산돌고딕 Neo",0,25));
					//bt[i].setBackground(Color.gray);
					bt[i].setForeground(new Color(85,14,14));
					add(bt[i]);
					bt[i].addActionListener(new MyListener());
				}
			}
		}
	}
	private class MyListener implements ActionListener{ //마우스 눌렸을 때 첫 글자 연산자 아니라면 스트링에 저장 
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			int n = label.getText().length();
			Character c = label.getText().charAt(n-1);
			if(flag==0&&c!='+'&&c!='-'&&c!='x'&&c!='/'&&c!='.'&&c!='^'||c=='0') {
				String oldtext = label.getText();
				String text = b.getText();
				String newtext=oldtext+text;
				label.setText(newtext);
				info.setForeground(Color.white);
				info.setFont(new Font("나눔고딕",0,20));
				info.setText("계산중입니다.");
			}
		}
	}
	public class CalcListener implements ActionListener{ //계산의 결과를 확인해서 info에 해당 내용 출력 
		public void actionPerformed(ActionEvent e) {
			String s = label.getText();
			double result = Calculator(s);
			label.setFont(new Font("Apple SD 산돌고딕 Neo",0,40));
			
			if(result<0) {
				info.setFont(new Font("나눔고딕",0,17));
				info.setForeground(new Color(85,14,14));
				info.setText("*양의 정수 범위의 계산만 할 수 있습니다.");
				label.setText(Double.toString(result));
				flag=1;
		
			}
			else if(result>=10000000)
			{
				info.setFont(new Font("나눔고딕",0,16));
				info.setForeground(new Color(85,14,14));
				info.setText("*10,000,000 이상의 수를 계산할 수 없습니다.");
				label.setText("0.0");
			}
			else label.setText(Double.toString(result));
			
		}
	} 
    public double Calculator(String ss) { //계산! 누적 입력 된 스트링을 파라미터로 받아와서 계산 return 값 double 타입으로
    	int i;  
    	double ans; 
    	check = 0; 
    	ArrayList<Double> v = new ArrayList<Double>(); //숫자, 점을 입력할 arraylist
    	ArrayList<String> op = new ArrayList<String>(); //연산자 저장해 놓을 arraylist
    	
    	op.add(null); //후 계산을 위해 연잔자 첫 칸 비워두기 
    	String str = new String("");
    	for(i=0; i<ss.length();i++) {
    		Character c = ss.charAt(i);
    		String s = Character.toString(c);
    		
    		if(Character.isDigit(c)) { //숫자라면 
    			str+=Character.toString(c);
    			if(i==ss.length()-1)
    					v.add(Double.parseDouble(str));
    		}
    		else if(s.equals(".")) str+=s; //점이어도 str에 누적해서 붙이기(연산자나오기 전까지)
    		else { //연산자 나오면 
    			v.add(Double.parseDouble(str)); //지금까지 찾아놓은 str v 배열에 담기 
    			op.add(Character.toString(c)); //연산자 배열에 저장 
    			str=""; //다음 수 입력받아야하니 str초기화 
    		}
    	}
    	for(i=0; i<v.size();i++) {
    		if(v.get(i)>=100000000) {
    			check=1;
    			info.setFont(new Font("나눔고딕",0,16));
    			info.setForeground(new Color(85,14,14));
    			info.setText("*10,000,000 미만의 수만 계산할 수 있습니다.");
    			break;
    		}
    	}
    	if(check==0) {
    		for(i=1; i<v.size(); i++) { //연산자 확인하면서 곱하기, 나누기, 제곱 먼저 저장해서 숫자 배열에 저장, 배열 갯수 하나 줄이기 
    			String s =op.get(i);
    			double tmp;
    			String t;
    			
    			if(s.equals("x")){
    				BigDecimal bigNumber1 = new BigDecimal(String.valueOf(v.get(i-1)));
    				BigDecimal bigNumber2 = new BigDecimal(String.valueOf(v.get(i)));
    				//bigNumber1.multiply(bigNumber2);
    				//tmp=v.get(i-1)*v.get(i);
    				tmp=bigNumber1.multiply(bigNumber2).doubleValue();
    				op.remove(i);
    				v.remove(i);
    				v.remove(i-1);
    				v.add(i-1,tmp);
    				i--;
    			}
    			else if(s.equals("/")) {
    				BigDecimal bigNumber3 = new BigDecimal(String.valueOf(v.get(i-1)));
    				BigDecimal bigNumber4 = new BigDecimal(String.valueOf(v.get(i)));
    				tmp=v.get(i-1)/v.get(i);
    				//tmp=bigNumber3.divide(bigNumber4).doubleValue();
    				op.remove(i);
    				v.remove(i);
    				v.remove(i-1);
    				v.add(i-1,tmp);
    				i--;
    			}
    			else if(s.equals("^")) {
    				tmp=Math.pow(v.get(i-1),v.get(i));
    				op.remove(i);
    				v.remove(i);
    				v.remove(i-1);
    				v.add(i-1,tmp);
    				i--;
    			}
    		}
    		ans=v.get(0); //숫자 배열 첫번째 값 ans에 저장 
    		for(i=1; i<v.size(); i++) { //남은 배열 갯수 기준으로 다시 돌면서 더하기, 빼기 계산하기  
    			String s = op.get(i);
    			double n = v.get(i);
    			
    			if(s.compareTo("+")==0)
    				ans=ans+n;
    			else if(s.compareTo("-")==0)
    				ans-=n;
    		}
    		return ans; //최종 계산 값 리턴 
    	}
    	return 0;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Practice f = new Practice();
		new Final_project();
	}
}
