package exemplodaogenerico;

import entidades.Cidade;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class TMCidade implements TableModel {

    private DAO<Cidade> dao;
    private List<Cidade> lista;

    public TMCidade() {
        dao = new DAO<Cidade>(Cidade.class);
        lista = dao.list();
    }
    
    public TMCidade(String filtro){
        dao = new DAO<Cidade>(Cidade.class);
        Cidade cid=new Cidade();
        cid.setNome(filtro);
        lista = dao.findByExamplePartial(cid);
    }

    public int getRowCount() { //Retorna quantas linhas tem na tabela

        return lista.size();
    }

    public int getColumnCount() { //Retorna quantas colunas tem na tabela

        return 3; //NOME, UF e POPULACAO

    }

    public String getColumnName(int columnIndex) { // Retorna o nome das colunas

        if (columnIndex == 0) {
            return "Cidade";
        } else if (columnIndex == 1) {
            return "UF";
        } else {
            return "População";
        }

    }

    // Retorna a classe das colunas
    public Class<?> getColumnClass(int columnIndex) { 
        if (columnIndex == 0) {
            return String.class;
        } else if (columnIndex == 1) {
            return String.class;
        } else {
            return Long.class;
        }
    }

    //Retorna se cada uma das céluas são ou não editáveis
    public boolean isCellEditable(int rowIndex, int columnIndex) { 
        return false;
    }

    //Método mais importante
    //Retorna qual é o valor ou o conteúdo de cada uma das células
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return lista.get(rowIndex).getNome();
        } else if (columnIndex == 1) {
            return lista.get(rowIndex).getUf();
        } else {
            return lista.get(rowIndex).getPopulacao();
        }
    }

    //Método que é executado quando o usuário alterar uma célula
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    public void addTableModelListener(TableModelListener l) {
    }


    public void removeTableModelListener(TableModelListener l) {
    }

    Cidade getElemento(int selecionado) {
        return lista.get(selecionado);
    }
}
