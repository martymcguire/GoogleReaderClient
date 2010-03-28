repositories.remote << 'http://www.ibiblio.org/maven2'

define 'google_reader_client' do
  project.version = '0.0.1'
  compile.with transitive('commons-httpclient:commons-httpclient:jar:3.1')
  package :jar
end
