import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        String sentence = br.readLine();
        int tagFlag = 0;
        /* 태그이면 바로 StringBuilder로 문자열에 추가해주고,
        *  태그가 아니라면 stack에 순서대로 push 해준 뒤,
        *  태그의 시작(<) 또는 공백 또는 마지막 문자가 나올때 (조건 1, 2, 3)
        *  stack에 담신 문자들을 모두 pop 해준다. -> 뒤집힌 문자 만들어짐*/
        for (int i=0;i<sentence.length();i++) {
            if (sentence.charAt(i) == '<') { // tag 시작 - stack에 있는 문자들 모두 pop (조건 1)
                tagFlag = 1;
                while (!stack.empty()) { // stack의 문자들 모두 pop
                    sb.append(stack.pop());
                }
            }

            if (tagFlag == 0) { // tag 아닐 때
                if (sentence.charAt(i) == ' ') { // tag 아니면서 공백이 등장 (조건 2)
                    while (!stack.empty()) { // stack의 문자들 모두 pop
                        sb.append(stack.pop());
                    }
                    sb.append(' ');
                } else { // tag 아니고 공백도 아니라면
                    stack.push(sentence.charAt(i)); // stack에 문자 넣어줌
                }

            } else { // tag 맞을 때
                sb.append(sentence.charAt(i));
                if (sentence.charAt(i) == '>') { // tag 끝나면 알림
                    tagFlag = 0;
                }
            }

            if (i+1 == sentence.length()) { // 문자열의 마지막 문자인 경우 (조건 3)
                while (!stack.empty()) { // stack의 문자들 모두 pop
                    sb.append(stack.pop());
                }
            }
        }
        System.out.print(sb);
        br.close();
    }
}
