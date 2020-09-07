package com.divinkas.app.words.utils

object ConstRoom {
    const val DB_NAME = "eng_words"
    const val DB_VERSION = 1

    const val TABLE_WORD = "words"
    const val TABLE_CATEGORY = "categories"
    const val TABLE_QUESTION_WORD = "question_word"

    const val TB_WORD_ID = "id"
    const val TB_WORD_WORD = "word"
    const val TB_WORD_TRANSLATE = "translate"
    const val TB_WORD_KOD_CATEGORY = "kod_category"
    const val TB_WORD_DATE_ADDED = "add_date"
    const val TB_WORD_DATE_LAST_VISIBLE = "last_visible_date"
    const val TB_WORD_IS_LEARNED = "is_learned"
    const val TB_WORD_RIGHT_ANSWERS = "right_answers"
    const val TB_WORD_WRONG_ANSWERS = "wrong_answers"

    const val TB_CATEGORY_ID = "id"
    const val TB_CATEGORY_NAME = "category_name"

    const val TB_QW_ID = "id"
    const val TB_QW_WORD = "word"
    const val TB_QW_QUESTION = "word_question"
    const val TB_QW_IS_LEARNED = "is_learned"
    const val TB_QW_RIGHT_ANSWERS = "right_answers"
    const val TB_QW_WRONG_ANSWERS = "wrong_answers"
}