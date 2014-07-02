import sys
import urllib2
import re

url="http://deals.ebay.in/category/Mobilephones"

content = urllib2.urlopen(url).read()
regex = "</p></a> </div>";
itemRegex = "<div class=\"item\" id=\"(.*?)\"><div class=\".*?\"> <b></b> <a href=\"(.*?)\" target=\".*?\"><img src=\"(.*?)\" /></a> </div><div class=\".*?\">.*?</div> <p class=\".*?\">.*?</p><a href=\".*?\" class=\".*?\" data-title=\".*?\" target=\".*?\"><strong>(.*?)</strong><span>(.*?)</span><p>(.*?)";
pattern = re.search(r"<div class=\"res-all su\">(.*?)<div id=\"pendingAspects\">", content)
if pattern:
    matchedContent = pattern.group()
    items = matchedContent.split(regex)
    for item in items:
        matches = re.match(itemRegex, item)
        if matches:
            print "Item Id:" + matches.group(1)
            print "Item URL:" + matches.group(2)
            print "Item Image:" + matches.group(3)
            print "Item Name:" + matches.group(4)
            print "Item Price:" + matches.group(5)
            print "--------------------------------\n"
