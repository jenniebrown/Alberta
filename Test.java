import org.python.util.PythonInterpreter;
import org.python.core.PyObject;
// import org.python.core.PyType;
import org.python.core.PyDictionary;
import org.python.core.PyFunction;
// import org.python.modules.cPickle;

class Test {
	public static void main(String[] args) {
		PythonInterpreter interpreter = new PythonInterpreter();
		
		interpreter.exec("from data import *\nfrom alberta import *");

		// PyType Product = (PyType) interpreter.get("Product");
		// PyType Sale = (PyType) interpreter.get("Sale");

		PyFunction startup = (PyFunction) interpreter.get("startup");
		PyFunction shutdown = (PyFunction) interpreter.get("shutdown");
		PyFunction sale = (PyFunction) interpreter.get("sale");

		startup.__call__();
		sale.__call__();

		interpreter.exec("from alberta import products, sales");
		PyDictionary products = (PyDictionary) interpreter.get("products");
		PyDictionary sales = (PyDictionary) interpreter.get("sales");

		//PyDictionary test = (PyDictionary) cPickle.load(interpreter.eval("open('products', 'r')"));
		
		for (PyObject i : products.itervalues().asIterable()) System.out.println(i.__str__());
		System.out.println();
		for (PyObject i : sales.itervalues().asIterable()) System.out.println(i.__str__());

		shutdown.__call__();
	}
}