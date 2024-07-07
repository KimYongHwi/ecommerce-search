package kyh.ecommerce.search.application.usecase.dto

class Parameter {

    data class Search(val keyword: String, val page: Int, val size: Int) {
        fun getFrom(): Int {
            return this.page * this.size
        }
    }
}
