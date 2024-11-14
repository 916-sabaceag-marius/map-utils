package utils;

import model.statement.CompundStatement;
import model.statement.IStatement;
import model.statement.NullStatement;

import java.util.List;

public final class Utils {

    private Utils(){}

    public static IStatement getStatement(List<IStatement> statements){

        if(statements.isEmpty()) return new NullStatement();

        if(statements.size() == 1) return statements.getFirst();

        // Get the last 2 statements
        IStatement statement = new CompundStatement(statements.get(statements.size()-2),statements.getLast());

        // iterate through the statements in reverse order

        for(int i = statements.size() - 3; i >= 0; i--){

            statement = new CompundStatement(statements.get(i),statement);
        }

        return statement;
    }

}
