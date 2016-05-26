package finchtemplate.util.hawk.validate

import finchtemplate.util.hawk._
import finchtemplate.util.hawk.params.{HeaderContext, KeyData}

object HeaderHasher {
  def hash(key: KeyData, header: HeaderContext): Hash = {
    val normalisedRequestString =
      s"""
         |$HawkVersionHeader
         |${header.authHeader.timestamp}
         |${header.authHeader.nonce}
         |${header.method.headerCanonicalForm}
         |${header.path.path}
         |${header.host.host}
         |${header.port.port}
         |
         |${header.authHeader.extendedData}
    """.stripMargin.trim
    Hash.computeHash(normalisedRequestString, key.algorithm)
  }
}
