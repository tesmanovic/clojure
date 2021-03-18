# RSS Feed Reader in Clojure
This console application represent implementation of RSS Feed Reader in Clojure. 
RSS feed allows users and applications to access updates to websites in a standardized, 
computer-readable format. RSS feed is in XML format.

# Get feed and read xml
Application starts with list of commands. 
Command :ls will list all feeds from config file.
After user choose one feed, program calls method which make web request and get xlm file from that link.
Each <item> element in xml file defines an article or "story" in the RSS feed. 
The <item> element has three required child elements:
	<title> - Defines the title of the item
	<link> - Defines the hyperlink to the item 
	<description> - Describes the item
User need to choose one story and program will show formatted text for that xml element. 

## License

Copyright © 2021

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
