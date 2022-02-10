class StoreItem(var pid: Int, var pname: String, var pvalue: Int) extends Artikel with Logger{

  var id = pid;
  var name = pname;
  var value = pvalue;

  override def logAction(actionName: String, name: String): Unit = {
    println(s"$name $actionName")
  }
}
