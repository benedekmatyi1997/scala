class Database extends Warenkorb {
  private var storedItems = Array[StoreItem]();

  override def delete(id: Int): Array[StoreItem] = {
    var newItems = storedItems.filter( _.id != id)

    var deletedElem = storedItems.filter( _.id == id)

    if(deletedElem.size < 1){
      throw new RuntimeException("Id "+ id +" nicht gefunden");
      return storedItems
    }
    storedItems = newItems;
    for(i<-deletedElem){
      i.logAction("gelöscht", i.name);
    }
    return storedItems;
  }
  override def search(name: String): Array[StoreItem] = {
    var foundItems = storedItems.filter( _.name == name);

    for(i <- foundItems){
      i.logAction("gefunden", i.name);
    }

    return storedItems;
  }
  override def sortByValueAsc(): Array[StoreItem] = {
    return storedItems.sortBy(_.value);
  }
  override def sortByValueDesc(): Array[StoreItem] = {
    return sortByValueAsc().reverse;
  }
  override def store(item: StoreItem): Array[StoreItem] = {
    var inThere = false;
    for(i<- storedItems){
      if(i.id == item.id){
        inThere = true;
      }
    }
    if(!inThere){
      storedItems = storedItems :+ item;
      item.logAction("gespeichert", item.name)
    }

    return storedItems;
  }
  override def sumUp(): Int = {
    var result = for{i<-storedItems} yield { i.value }
    return result.sum;

  }

  def higherThan(value: Int): Array[StoreItem] = {
    val asd = storedItems
      .filter( (s: StoreItem) => s.value > value )
      .sortBy( _.value )
    asd.foreach( x => x.logAction("enthalten", x.name) )
    asd
  }

  def getStoreItems(): Array[StoreItem] = {
    val asd = storedItems
    asd
  }

  def filterByName(name: String, items: Array[StoreItem]): Array[StoreItem] = {
    val asd = items.filter(_.name == name).sortBy(_.value)
    asd.foreach( x => x.logAction("ist drin", x.name))
    asd
  }

}
