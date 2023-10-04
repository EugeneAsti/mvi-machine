package ru.aeyu.mvi_machine.mvi_machine

/**
 * Класс метка для обозначения пользовательских действий
 */
interface ViewIntent

interface UserIntent : ViewIntent

interface InternalIntent : ViewIntent