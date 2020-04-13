@echo off
rem run following bat to generate new ascii encoded properties files
rem set java_home=c:\java\jdk1.8
"%java_home%/bin/native2ascii" -encoding UTF-8 ajaxswing_messages_de_utf8.properties ../ajaxswing_messages_de.properties
"%java_home%/bin/native2ascii" -encoding UTF-8 ajaxswing_messages_es_utf8.properties ../ajaxswing_messages_es.properties
"%java_home%/bin/native2ascii" -encoding UTF-8 ajaxswing_messages_fr_utf8.properties ../ajaxswing_messages_fr.properties
"%java_home%/bin/native2ascii" -encoding UTF-8 ajaxswing_messages_it_utf8.properties ../ajaxswing_messages_it.properties
"%java_home%/bin/native2ascii" -encoding UTF-8 ajaxswing_messages_ja_utf8.properties ../ajaxswing_messages_ja.properties
"%java_home%/bin/native2ascii" -encoding UTF-8 ajaxswing_messages_zh_utf8.properties ../ajaxswing_messages_zh.properties