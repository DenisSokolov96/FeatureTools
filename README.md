# FeatureTools
## Monadic calculator
`It provides three public methods.
The static of method creates a new instance of the calculator with a specified initial value; it is an implementation of the unit operation.
The eval method applies a given function to the value stored in the calculator. The method returns a new monad that is obtained after applying the given function to the value; it is an implementation of the map operation.
The consume method passes the stored value to a given consumer only if no errors have occurred in the calculator and, otherwise, it does nothing.`