package gini.apps.coins.network
fun interface ResultApi<D,E> {
   fun result(getData: D, getException: E)
}