package org.springboot.practice.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import org.passay.DefaultPasswordValidator;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.ValidationResult;
import org.passay.data.EnglishCharacterData;
import org.passay.resolver.PropertiesMessageResolver;
import org.passay.rule.CharacterCharacteristicsRule;
import org.passay.rule.CharacterRule;
import org.passay.rule.LengthRule;
import org.passay.rule.RepeatCharactersRule;
import org.passay.rule.Rule;

public class PasswordRuleValidator implements ConstraintValidator<Password, String> {

  private static final int MIN_COMPLEX_RULES = 2;
  private static final int REPETITIVE_CHARS_THRESHOLD = 3;
  private static final int MIN_SPECIAL_CASE_CHARS = 1;
  private static final int MIN_UPPER_CASE_CHARS = 1;
  private static final int MIN_LOWER_CASE_CHARS = 1;
  private static final int MIN_DIGIT_CASE_CHARS = 1;

  @Override
  public boolean isValid(String password, ConstraintValidatorContext context) {
    List<Rule> passwordRules = new ArrayList<>();
    // === 필수 규칙 ===
    // 암호 길이 최소 8자 최대 30자
    passwordRules.add(new LengthRule(8, 30));

    // 특수문자 필수
    passwordRules.add(new CharacterRule(EnglishCharacterData.Special, MIN_SPECIAL_CASE_CHARS));

    // 반복 룰 필수
    passwordRules.add(new RepeatCharactersRule(REPETITIVE_CHARS_THRESHOLD));

    // === 선택형 규칙 ===
    // 3종 규칙 중 2개 이상 허용되면 통과
    CharacterCharacteristicsRule characterCharacteristicsRule =
        new CharacterCharacteristicsRule(
            MIN_COMPLEX_RULES,
            new CharacterRule(EnglishCharacterData.UpperCase, MIN_UPPER_CASE_CHARS),
            new CharacterRule(EnglishCharacterData.LowerCase, MIN_LOWER_CASE_CHARS),
            new CharacterRule(EnglishCharacterData.Digit, MIN_DIGIT_CASE_CHARS));
    passwordRules.add(characterCharacteristicsRule);

    PasswordValidator passwordValidator =
        new DefaultPasswordValidator(new PropertiesMessageResolver(), passwordRules);

    PasswordData passwordData = new PasswordData(password);
    ValidationResult ruleResult = passwordValidator.validate(passwordData);

    if (ruleResult.isValid()) {
      return true;
    }

    // Passay의 상세 메시지를 Bean Validation violation으로 전달
    context.disableDefaultConstraintViolation();
    ruleResult
        .getMessages()
        .forEach(msg -> context.buildConstraintViolationWithTemplate(msg).addConstraintViolation());

    return ruleResult.isValid();
  }
}
