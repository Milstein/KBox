package org.aksw.kbox.kns;

import java.net.URL;

public class KNSResolverVisitor implements KNSServerListVisitor {
	
	private URL resourceURL = null;
	private KN resolvedKN = null;
	private String format = null;
	private String version = null;
	private Resolver resolver = null;
	
	public KNSResolverVisitor(URL resourceURL, Resolver resolver) {
		this.resourceURL = resourceURL;
		this.resolver = resolver;
	}

	public KNSResolverVisitor(URL resourceURL, String format, String version, Resolver resolver) {
		this(resourceURL, resolver);
		this.format = format;
		this.version = version;
	}

	@Override
	public boolean visit(KNSServer knsServer) throws Exception {
		KN kn = null;
		URL knsServerURL = knsServer.getURL();
		if(format == null && version == null) {
			kn = resolver.resolve(knsServerURL, resourceURL);
		} else if(format != null && version != null) {
			kn = resolver.resolve(knsServerURL, resourceURL, format, version);
		} else if(format != null) {
			kn = resolver.resolve(knsServerURL, resourceURL, format);
        }
		if(kn != null) {
			resolvedKN = kn;
			return false;
		}
		return true;
	}
	
	public KN getResolvedKN() {
		return resolvedKN;
	}
}
