# SearchTree
Makes a Trie using data from a list of models for specific attributes or all attributes from model.
This can used later for quickly searching a value in whole list and all attributes added to tree with support of configurations like searching prefix, case insensitive search, contains and suffix.
Additionally, it supports searching data in specific attributes only, instead of whole object.
It internaly uses reflection to populate tree.
Use Case: Datatables in UI or Forms where Large dataset is shown and user can search Column wise or at table level(Global search)
There is also a driver class to compare tree and normal search using loop.