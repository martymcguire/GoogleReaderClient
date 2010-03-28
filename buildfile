repositories.remote << 'http://www.ibiblio.org/maven2'

HTTPC = transitive('commons-httpclient:commons-httpclient:jar:3.1')
JACKSON = transitive('org.codehaus.jackson:jackson-mapper-lgpl:jar:1.5.0')
define 'google_reader_client' do
  project.version = '0.0.1'
  compile.with HTTPC, JACKSON
  package :jar
end
