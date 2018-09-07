# ShipViewer
I’ve made no attempts whatsoever to refactor this project. Everything is within ShipViewerScreen.java. 
This uses a PerspectiveCamera to render a TiledMap, an AssetManager to load large asset files, a TiledMapRenderer, and a crap attempt to convert screen coordinates to world coordinates. 
The correct method would’ve been to just use camera.unproject(scrX, scrY) instead of all that linear algebra.
