package mocks;

import java.io.IOException;

/**
 * This is a fake appendable class that throws
 * an exception when append is called.
 */
public class FakeAppendable implements Appendable {
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Fake appendable throws exception.");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Fake appendable throws exception.");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Fake appendable throws exception.");
  }
}
