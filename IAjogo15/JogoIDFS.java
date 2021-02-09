import java.util.Scanner;
import java.util.Stack;
//import java.lang.Integer;


class JogoIDFS{
	public static int xPos;
	public static int yPos;
	public static int totalNodes = 0;
	public static Config ini = new Config();
	public static Config fin = new Config();
	
	//criar nós descendentes
	public static Stack<Config> MakeDescendants(Config node, Stack<Config> stack){
		
		Config newConfig;
		findZero(node);

		//mover cima
		if(xPos - 1 >= 0 && node.play != 'B'){
			newConfig = new Config(node.level + 1, 'C', node.path + 'C', node);
			newConfig.pos[xPos][yPos] = newConfig.pos[xPos - 1][yPos];
			newConfig.pos[xPos - 1][yPos] = 0;
			stack.push(newConfig);
			totalNodes++;
		}
		

		//mover baixo
		if(xPos + 1 < 4 && node.play != 'C'){
			newConfig = new Config(node.level + 1, 'B', node.path + 'B', node);
			newConfig.pos[xPos][yPos] = newConfig.pos[xPos + 1][yPos];
			newConfig.pos[xPos + 1][yPos] = 0;
			stack.push(newConfig);
			totalNodes++;
		}
		
		//mover esquerda
		if(yPos - 1 >= 0 && node.play != 'D'){
			newConfig = new Config(node.level + 1, 'E', node.path + 'E', node);
			newConfig.pos[xPos][yPos] = newConfig.pos[xPos][yPos - 1];
			newConfig.pos[xPos][yPos - 1] = 0;
			stack.push(newConfig);
			totalNodes++;
		}
		
		//mover direita
		if(yPos + 1 < 4 && node.play != 'E'){
			newConfig = new Config(node.level + 1, 'D', node.path + 'D', node);
			newConfig.pos[xPos][yPos] = newConfig.pos[xPos][yPos + 1];
			newConfig.pos[xPos][yPos + 1] = 0;
			stack.push(newConfig);
			totalNodes++;
		}
		

		return stack;
	}
		//algoritmo de procura
		public static void generalSearchAlgorithm(){
			long iniTime = System.currentTimeMillis();
			Config node = new Config();
			int lv = 1;
			String finSol = fin.HashCode();
			Stack<Config> stack;

			
			while(true){
				stack = new Stack<Config>();
				stack.push(ini);
				while(!stack.empty()){
					node = stack.pop();
					if(node.level < lv){
						//se for solução
						if(node.HashCode().equals(finSol)){
							long totalTime = System.currentTimeMillis();
							System.out.println("Tempo:" + (totalTime - iniTime) + "ms");
							System.out.println("Numero total de nos:" + totalNodes);
							System.out.println("Nivel:" + node.level);
							System.out.println("Caminho:" + node.path);
							return;
						}
					stack = MakeDescendants(node, stack);
					}
				}
				lv++;
			}
		}
		//procurar zero
		public static void findZero(Config node){
			for(int i = 0; i < 4; i++)
				for(int j = 0; j < 4; j++)
					if(node.pos[i][j] == 0){
						xPos = i;
						yPos = j;
						return;
					}
			return;		
		}
		
		//main
		public static void main(String[] args){
			Scanner in = new Scanner(System.in);
			
			
			System.out.println("Configuracao inicial:");
			ini.readConfig(in);
			System.out.println();
			
			System.out.println("Configuracao final:");
			fin.readConfig(in);
			System.out.println();

			 //check Solvability
    		boolean solvIni = ini.solvability();
    		boolean solvFin = fin.solvability();
    		if( (solvIni && solvFin) || (!solvIni && !solvFin) )
      			generalSearchAlgorithm();
    		else
      		System.out.println("Nao tem solucao!");
		}
}