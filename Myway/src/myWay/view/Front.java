package myWay.view;

public class Front {
	//싱글톤 생성
	//내부 객체 만들기 
	private static Front front = new Front();
	//빈생성자 
	private Front(){}
	//외부에서 호출 가능한 내부객체 반환 메소드 [getInstance]
	public static Front getInstance () {return front;}
	
	
	// 글씨 꾸미기 변수 저장.. (어디다 해야 될 지 몰라서 일단 임시로 입력해 둠)
		public static final String RESET = "\u001B[0m";    
		public static final String FONT_BLACK = "\u001B[30m";    
		public static final String FONT_RED = "\u001B[31m";     
		public static final String FONT_GREEN = "\u001B[32m";    
		public static final String FONT_YELLOW = "\u001B[33m";     
		public static final String FONT_BLUE = "\u001B[34m";   
		public static final String FONT_PURPLE = "\u001B[35m";     
		public static final String FONT_CYAN = "\u001B[36m";    
		public static final String FONT_WHITE = "\u001B[37m";  
		public static final String FONT_PINK = "\u001B[95m";  
		
		public static final String BACKGROUND_BLACK = "\u001B[40m";     
		public static final String BACKGROUND_RED = "\u001B[41m";     
		public static final String BACKGROUND_GREEN = "\u001B[42m";    
		public static final String BACKGROUND_YELLOW = "\u001B[43m";     
		public static final String BACKGROUND_BLUE = "\u001B[44m";    
		public static final String BACKGROUND_PURPLE = "\u001B[45m";     
		public static final String BACKGROUND_CYAN = "\u001B[46m";     
		public static final String BACKGROUND_WHITE = "\u001B[47m";
		
		public void hi() {
			System.out.println("________________________________________________________\n");
			System.out.println(FONT_YELLOW + ".___  ___. ____    ____ \n"
					+ "|   \\/   | \\   \\  /   / \n"
					+ "|  \\  /  |  \\   \\/   /  \n"
					+ "|  |\\/|  |   \\_    _/   \n"
					+ "|  |  |  |     |  |     \n"
					+ "|__|  |__|     |__|     \n"
					+ RESET);
			System.out.println(FONT_GREEN+"____    __    ____      ___      ____    ____ \n"
					+ "\\   \\  /  \\  /   /     /   \\     \\   \\  /   / \n"
					+ " \\   \\/    \\/   /     /  ^  \\     \\   \\/   /  \n"
					+ "  \\            /     /  /_\\  \\     \\_    _/   \n"
					+ "   \\    /\\    /     /  _____  \\      |  |     \n"
					+ "    \\__/  \\__/     /__/     \\__\\     |__|     \n"
					+ RESET);
			System.out.println("________________________________________________________");
			
		}
		
		
}
