package orj.jelda.abstracts;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;

import org.jelda.player.Write;
import org.jelda.quest.manifest.Manifest;

public abstract class Pool<V> {
	protected File loadDir;
	protected Manifest manifest;
	protected HashMap<String, String> idToFilenameMap;
	protected HashMap<String, V> idToInstanceMap;
	public Pool(File loadDir) {
		this.loadDir = loadDir;
		idToFilenameMap = new HashMap<String, String>();
		manifest = new Manifest();
		idToInstanceMap = new HashMap<String, V>();
		init();
	}
	
	protected abstract void init();
	
	public V getInstance(String id) {
		if (idToFilenameMap.containsKey(id)) {
			if (idToInstanceMap.containsKey(id)) {
				return idToInstanceMap.get(id);
			}
			else {
				V instance = load(id);
				idToInstanceMap.put(id, instance);
				return instance;
			}
		}
		else {
			Write.error("No instances exists with ID " +id);
			return null;
		}
	}
	
	public abstract V load(String id);
	
	public void unloadAll() {
		idToInstanceMap.clear();
	}
	
	public void unload(String id) {
		V temp = idToInstanceMap.get(id);
		if (temp != null) {
			idToInstanceMap.remove(temp);
		}
	}
	
	public void loadAll(Collection<String> ids, boolean pruneOld) {
		if (pruneOld)
			idToInstanceMap.keySet().retainAll(ids);
		for (String s : ids)
			if (!idToInstanceMap.containsKey(s)) {
				getInstance(s);
			}
	}
	
}
