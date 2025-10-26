package com.avazquez.api.marvel.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * MD5 password encoder implementation for Marvel API hash generation.
 *
 * <p>This component provides MD5 hashing functionality required for Marvel API authentication. The
 * Marvel Developer API requires authentication using a hash generated from timestamp, private key,
 * and public key using MD5 algorithm.
 *
 * <p>Security Note: MD5 is used here only because it's specifically required by the Marvel API. For
 * general password hashing, stronger algorithms like BCrypt should be used.
 *
 * @author Alex Vazquez
 * @version 1.0
 * @since 1.0
 * @see org.springframework.security.crypto.password.PasswordEncoder
 */
@Component("md5Encoder")
public class MsdEncoder implements PasswordEncoder {

  /**
   * Encodes the given raw password using MD5 hash algorithm.
   *
   * @param rawPassword the raw password to encode (timestamp + privateKey + publicKey for Marvel
   *     API)
   * @return the MD5 hash as hexadecimal string
   */
  @Override
  public String encode(CharSequence rawPassword) {
    return DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
  }

  /**
   * Verifies if the raw password matches the encoded password.
   *
   * @param rawPassword the raw password to check
   * @param encodedPassword the encoded password to verify against
   * @return true if passwords match, false otherwise
   */
  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    return encodedPassword.equals(encode(rawPassword));
  }
}
