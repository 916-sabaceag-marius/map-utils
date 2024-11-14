import controller.Controller;
import model.expressions.*;
import model.repository.IRepository;
import model.repository.Repository;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.type.ReferenceType;
import model.type.StringType;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import utils.Utils;
import view.TextMenu;
import view.commands.ExitCommand;
import view.commands.RunProgramCommand;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<IStatement> statements2 = new ArrayList<>();

        statements2.add(new VariableDeclarationStatement("a",new IntType()));
        statements2.add(new VariableDeclarationStatement("b",new IntType()));
        statements2.add(new AssignStatement("a",
                new ArithmeticalExpression('+',
                        new ValueExpression(new IntValue(2)),
                        new ArithmeticalExpression('*',
                                new ValueExpression(new IntValue(3)),
                                new ValueExpression(new IntValue(5))))));

        statements2.add(new AssignStatement("b",
                new ArithmeticalExpression('+',
                        new VariableExpression("a"),
                        new ValueExpression(new IntValue(1)))));
        statements2.add(new PrintStatement(new VariableExpression("b")));

        IStatement ex2 = Utils.getStatement(statements2);

        //Same thing as:
        
//        IStatement ex2 = new CompundStatement( new VariableDeclarationStatement("a",new IntType()),
//                new CompundStatement(new VariableDeclarationStatement("b",new IntType()),
//                        new CompundStatement(new AssignStatement("a", new ArithmeticalExpression('+',new ValueExpression(new IntValue(2)),new ArithmeticalExpression('*',new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
//                                new CompundStatement(new AssignStatement("b",new ArithmeticalExpression('+',new VariableExpression("a"), new ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));

        IRepository repository2 = new Repository("log2.txt");

        repository2.addProgramState(ex2);

        Controller controller2 = new Controller(repository2);

        var menu = new TextMenu();

        menu.addCommand(new ExitCommand("0","Close the program"));
        menu.addCommand(new RunProgramCommand(controller2,"2",ex2.toString()));
        menu.show();
    }



}

