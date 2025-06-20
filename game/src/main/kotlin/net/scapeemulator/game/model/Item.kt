package net.scapeemulator.game.model

open class Item(id: Int, amount: Int = 1) : Node() {
    val id: Int
    val amount: Int

    //todo remove
    val definition: ItemDefinition? get() = ItemDefinitions.forId(id)
    val equipmentDefinition: EquipmentDefinition? get() = EquipmentDefinition.forId(id)

    init {
        require(amount >= 0)
        this.id = id
        this.amount = amount
    }

    override var index = -1

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + amount
        result = prime * result + id
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        if (javaClass != other.javaClass) return false
        val other1 = other as Item
        if (amount != other1.amount) return false
        if (id != other1.id) return false
        return true
    }

    override fun toString(): String = Item::class.java.simpleName + " [id=" + id + ", amount=" + amount + "]"
}
