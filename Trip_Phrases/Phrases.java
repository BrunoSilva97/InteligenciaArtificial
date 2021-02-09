import java.util.*;

class Phrase{
	boolean sexo;
	boolean singular;
	String frase[];
	String aux[];

	Phrase(String phrase){
		frase = phrase.split("\\s");
		aux = new String[frase.length];
	}

	public boolean check(boolean sexo, boolean singular){
		if(this.sexo == sexo && this.singular == singular)
			return true;
		else
			return false;
	}

	public void inicial(boolean sexo, boolean singular){
		this.sexo = sexo;
		this.singular = singular;
	}

	public void print(){
		System.out.print("frase nominal(" + aux[0] + "(" + frase[0]+")),(" + aux[1] + "(" + frase[1] + ")), frase verbal(" );
	    for(int i=2; i < frase.length - 1; i++)
           	System.out.print(aux[i]+"("+frase[i]+"),");
        System.out.println(aux[frase.length - 1]+"("+ frase[frase.length - 1]+"))");
	}

	public boolean verbo_singular(boolean singular){
		return (this.singular == singular);
	}
}
	
class BaseDados{

	String determinante_f[] = {"A", "a"};
	String determinante_m[] = {"O", "o"};
	String determinante_f_p[] = {"As", "as"};
	String determinante_m_p[] = {"Os", "os"};

	String nome_f[] = {"menina", "floresta", "mae", "vida", "noticia", "cidade", "porta"};
	String nome_m[] = {"menino", "tempo", "cacador", "rosto", "rio", "mar", "vento", "martelo", "cachorro", "tambor", "sino"};
	String nome_f_p[] = {"lagrimas"};
	String nome_m_p[] = {"tambores", "lobos"};

	String preposicao[] = {"para", "com"};
	String preposicao_f[] = {"pela"};
	String preposicao_m[] = {"pelo"};

	String contracao_f[] = {"na"};
	String contracao_m[] = {"no"};
	String contracao_f_p[] = {"nas"};
	String contracao_m_p[] = {"nos"};

	String verbo[] = {"corre", "correu", "bateu"};
	String verbo_p[] = {"corriam", "bateram", "correram"};

	public void testar(Phrase phrase){
		if(phrase.frase.length > 3){
			if(nome(phrase) && predicado(phrase,3))
				phrase.print();
		}
		else if(nome(phrase) && phrase.frase.length <= 3)
			phrase.print();
	}

	public boolean question(String palavra, String tipo[]){
		for (String aux : tipo){
			if (palavra.equals(aux))
				return true;
		}
		return false;
	}

	public boolean nome(Phrase phrase){
		return(artigo(phrase,0) && substantivo(phrase,1) && verbo(phrase,2));
	}

	public boolean artigo(Phrase phrase,int i){
		String palavra = phrase.frase[i];
		phrase.aux[i] = "artigo";
		if(question(palavra,determinante_f))
			phrase.inicial(false,true);
		else if(question(palavra,determinante_f_p))
			phrase.inicial(false,false);
		else if(question(palavra,determinante_m))
			phrase.inicial(true,true);
		else if(question(palavra,determinante_m_p))
			phrase.inicial(true,false);
		else
			return false;
		return true;
	}

	public boolean substantivo(Phrase phrase,int i){
		String palavra = phrase.frase[i];
		phrase.aux[i] = "substantivo";
		if(question(palavra,nome_f) && phrase.check(false,true))
			return true;
		else if(question(palavra,nome_m) && phrase.check(true,true))
			return true;
		if(question(palavra,nome_f_p) && phrase.check(false,false))
			return true;
		else if(question(palavra,nome_m_p) && phrase.check(true,false))
			return true;
		else{
			System.out.println(palavra + " erro");
			return false;
		}
	}

	public boolean verbo(Phrase phrase,int i){
		String palavra = phrase.frase[i];
		phrase.aux[i] = "verbo";
		if(question(palavra,verbo) && phrase.verbo_singular(true))
			return true;
		else if(question(palavra,verbo_p) && phrase.verbo_singular(false))
			return true;
		else{
			System.out.println(palavra + " erro");
			return false;
		}
	}

	public boolean predicado(Phrase phrase, int i){
		String palavra = phrase.frase[i];
		if(question(palavra,preposicao_f)){
			phrase.aux[i] = "preposicao";
			phrase.inicial(false,true);
			i++;
			if(!substantivo(phrase,i))
				return false;
			if(i < phrase.frase.length-1)
				if(!predicado(phrase,i++)){
					System.out.println(palavra + " erro");
					return false;
				}
		}

		else if(question(palavra,preposicao_m)){
			phrase.aux[i] = "preposicao";
			phrase.inicial(true,true);
			i++;
			if(!substantivo(phrase,i))
				return false;
			if(i < phrase.frase.length-1)
				if(!predicado(phrase,i++)){
					System.out.println(palavra + " erro");		
					return false;
				}
		return true;
		}

		else if(question(palavra,preposicao)){
			phrase.aux[i] = "preposicao";
			i++;
			if(!artigo(phrase,i))
				return false;
			if(i < phrase.frase.length-1)
				if(!predicado(phrase,i++)){
					System.out.println(palavra + " erro");
					return false;
				}
		}
		else if(question(palavra,preposicao)){
			phrase.aux[i] = "preposicao";
			i++;
			if(!artigo(phrase,i))
				return false;
			i++;
			if(!substantivo(phrase,i))
				return false;
			if(i < phrase.frase.length-1)
				if(!predicado(phrase,i++)){
					System.out.println(palavra + " erro");
					return false;
				}
		}

		else if( question(palavra, determinante_f)){
			phrase.inicial(false,true);
			phrase.aux[i] = "artigo";
			i++;
			if(!substantivo(phrase,i))
				return false;
			if(i < phrase.frase.length-1)
				if(!predicado(phrase,i++)){
					System.out.println(palavra + " erro");
					return false;
				}
		}

		else if(question(palavra, determinante_m)){
			phrase.inicial(true,true);
			phrase.aux[i] = "artigo";
			i++;
			if(!substantivo(phrase,i))
				return false;
			if(i < phrase.frase.length-1)
				if(!predicado(phrase,i++)){
					System.out.println(palavra + " erro");
					return false;
				}
		}
		else if(question(palavra, determinante_f_p)){
			phrase.inicial(false,false);
			phrase.aux[i] = "artigo";
			i++;
			if(!substantivo(phrase,i))
				return false;
			if(i < phrase.frase.length-1)
				if(!predicado(phrase,i++)){
					System.out.println(palavra + " erro");
					return false;
				}
		}

		else if(question(palavra, determinante_m_p)){
			phrase.inicial(true,false);
			phrase.aux[i] = "artigo";
			i++;
			if(!substantivo(phrase,i))
				return false;
			if(i < phrase.frase.length-1)
				if(!predicado(phrase,i++)){
					System.out.println(palavra + " erro");
					return false;
				}
		}
		else if (question(palavra, contracao_f)){
			phrase.inicial(false,true);
			phrase.aux[i] = "contracao";
			i++;
			if(!substantivo(phrase,i))
				return false;
			if(i < phrase.frase.length-1)
				if(!predicado(phrase,i++)){
					System.out.println(palavra + " erro");
					return false;
				}
		}
		else if (question(palavra, contracao_m)){
			phrase.inicial(true,true);
			phrase.aux[i] = "contracao";
			i++;
			if(!substantivo(phrase,i))
				return false;
			if(i < phrase.frase.length-1)
				if(!predicado(phrase,i++)){
					System.out.println(palavra + " erro");
					return false;
				}
		}
		else if (question(palavra, contracao_f_p)){
			phrase.inicial(false,false);
			phrase.aux[i] = "contracao";
			i++;
			if(!substantivo(phrase,i))
				return false;
			if(i < phrase.frase.length-1)
				if(!predicado(phrase,i++)){
					System.out.println(palavra + " erro");
					return false;
				}
		}
		else if (question(palavra, contracao_m_p)){
			phrase.inicial(true,false);
			phrase.aux[i] = "contracao";
			i++;
			if(!substantivo(phrase,i))
				return false;
			if(i < phrase.frase.length-1)
				if(!predicado(phrase,i++)){
					System.out.println(palavra + " erro");
					return false;
				}
		}
		else
			return false;
		return true;
	}
}

public class Phrases{
	
	public static void main(String[] args){
		int n = 1;
		Scanner in = new Scanner(System.in);
	
		System.out.println("Introduza a frase");
		Phrase phrase = new Phrase(in.nextLine());
		BaseDados bd = new BaseDados();
		bd.testar(phrase);
	}
}
