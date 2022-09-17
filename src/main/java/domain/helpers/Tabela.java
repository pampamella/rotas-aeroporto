package domain.helpers;

import domain.model.Aeroporto;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;

import java.util.ArrayList;

public class Tabela {
    public static Table montaTabela(ArrayList<Aeroporto> lista) {
        ArrayList<String> codigos = new ArrayList<>();
        ArrayList<String> nomes = new ArrayList<>();
        ArrayList<String> cidades = new ArrayList<>();
        ArrayList<String> estados = new ArrayList<>();

        for (Aeroporto aeroporto : lista) {
            codigos.add(aeroporto.getCodigo());
            nomes.add(aeroporto.getNomeCompleto());
            cidades.add(aeroporto.getCidade());
            estados.add(aeroporto.getEstado());
        }

        Table tabela = Table.create("Aeroportos");
        StringColumn codigoCol = StringColumn.create("Código", codigos);
        StringColumn nomesCol = StringColumn.create("Nome", nomes);
        StringColumn cidadesCol = StringColumn.create("Cidade", cidades);
        StringColumn estadosCol = StringColumn.create("Estado", estados);
        tabela.addColumns(codigoCol, nomesCol, cidadesCol, estadosCol);

        return tabela;
    }
}
